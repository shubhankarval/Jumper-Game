package physics

import physics.objects.{DynamicObject, GameObject, StaticObject}

/**
  * Controls and computes the simulated physics for a game. Manages dynamic object movement, gravity, and
  * object collisions
  */
object PhysicsEngine {

  def isCollision(a:GameObject,b:GameObject):Boolean={

    val a_max = new PhysicsVector(a.location.x+a.dimensions.x,a.location.y+a.dimensions.y,a.location.z+a.dimensions.z)
    val b_max = new PhysicsVector(b.location.x+b.dimensions.x,b.location.y+b.dimensions.y,b.location.z+b.dimensions.z)
    a.location.x<b_max.x && b.location.x<a_max.x && a.location.y<b_max.y && b.location.y<a_max.y && a.location.z<b_max.z && b.location.z<a_max.z

  }

  def updateObject(obj:DynamicObject,time:Double,gravity:Double):Unit={
    obj.velocity.z = -gravity*time + obj.velocity.z
    obj.previousLocation.x=obj.location.x
    obj.previousLocation.y=obj.location.y
    obj.previousLocation.z=obj.location.z
    obj.location.x=obj.velocity.x*time+obj.location.x
    obj.location.y=obj.velocity.y*time+obj.location.y
    obj.location.z=obj.velocity.z*time+obj.location.z

    if(obj.location.z <= 0.0){
      obj.location.z=0.0
      obj.velocity.z=0.0
      obj.onGround()
    }
  }

  def checkStaticCollision(dynamicObject: DynamicObject, staticObject: StaticObject):Unit={
    if(isCollision(dynamicObject,staticObject)){
      dynamicObject.collideWithStaticObject(staticObject)
      val d = new DynamicObject(dynamicObject.previousLocation,dynamicObject.dimensions)
      if(isCollision(d,staticObject)){
        staticObject.collideWithDynamicObject(dynamicObject,6)
      }
      else{
        staticObject.collideWithDynamicObject(dynamicObject,staticHelper(dynamicObject,staticObject))
      }
    }
  }

  def staticHelper(d: DynamicObject, s: StaticObject):Int={
    val dmax=new PhysicsVector(d.previousLocation.x+d.dimensions.x,d.previousLocation.y+d.dimensions.y,d.previousLocation.z+d.dimensions.z)
    val smax=new PhysicsVector(s.location.x+s.dimensions.x,s.location.y+s.dimensions.y,s.location.z+s.dimensions.z)

    if(d.previousLocation.z>=smax.z){
      //top
      return 1
    }
    if(dmax.z<=s.location.z){
      //bottom
      return 0
    }
    if(dmax.x<=s.location.x){
      //static faces left
      return 3
    }
    if(d.previousLocation.x>=smax.x){
      //faces right
      return 2
    }
    6
  }

  def updateWorld(world: World, dt: Double): Unit = {
    for(d <- world.dynamicObjects){
      updateObject(d,dt,world.gravity)
    }
    for(d <- world.dynamicObjects){
      for(s<-world.staticObjects){
        checkStaticCollision(d,s)
      }
    }

  }

  def dynamicCollision(a:DynamicObject,b:DynamicObject):Unit={
    if(isCollision(a,b)){
      a.velocity.x = ((a.mass - b.mass)*a.velocity.x + (2*b.mass*b.velocity.x))/(a.mass+b.mass)
      a.velocity.y = ((a.mass - b.mass)*a.velocity.y + (2*b.mass*b.velocity.y))/(a.mass+b.mass)
      a.velocity.z = ((a.mass - b.mass)*a.velocity.z + (2*b.mass*b.velocity.z))/(a.mass+b.mass)
      b.velocity.x = ((b.mass - a.mass)*b.velocity.x + (2*a.mass*a.velocity.x))/(a.mass+b.mass)
      b.velocity.y = ((b.mass - a.mass)*b.velocity.y + (2*a.mass*a.velocity.y))/(a.mass+b.mass)
      b.velocity.z = ((b.mass - a.mass)*b.velocity.z + (2*a.mass*a.velocity.z))/(a.mass+b.mass)
    }
  }

  def main(args: Array[String]): Unit = {
    val a = new DynamicObject(new PhysicsVector(-0.1,-1.0,0.0), new PhysicsVector(1.0,1.0,1.0))
    val b = new DynamicObject(new PhysicsVector(0.1,-1.0,0.0), new PhysicsVector(1.0,1.0,1.0))
    a.velocity = new PhysicsVector(2.0,1.5,0.0)
    b.velocity = new PhysicsVector(-2.0,1.0,0.0)
    a.mass=2.0
    b.mass=4.0
    dynamicCollision(a,b)
    println(a.velocity)
    println(b.velocity)

  }

}

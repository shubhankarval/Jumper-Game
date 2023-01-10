package tests
import org.scalatest._
import physics.PhysicsVector
import physics.objects.{DynamicObject, GameObject, StaticObject}

class TestStaticCollision extends FunSuite {
  test("static collision check"){
    val a=new DynamicObject(new PhysicsVector(0,0,0),new PhysicsVector(3,4,5))
    val b=new StaticObject(new PhysicsVector(-7,-8,-10),new PhysicsVector(7,8,9))
    val c=new StaticObject(new PhysicsVector(-5,-6,-7),new PhysicsVector(10,12,13))



  }
}

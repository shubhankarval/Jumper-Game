package tests

import org.scalatest._
import physics.{PhysicsVector,PhysicsEngine}
import physics.objects.GameObject

class TestCollisions extends FunSuite {


  test("Tests for accurate collision detection between two objects") {
    val a=new GameObject(new PhysicsVector(0,0,0),new PhysicsVector(3,4,5))
    val b=new GameObject(new PhysicsVector(-7,-8,-10),new PhysicsVector(7,8,9))
    val c=new GameObject(new PhysicsVector(-5,-6,-7),new PhysicsVector(10,12,13))
    assert(!PhysicsEngine.isCollision(a,b))
    assert(PhysicsEngine.isCollision(a,c))
  }


  /*

      val a_max = new PhysicsVector(a.location.x+a.dimensions.x,a.location.y+a.dimensions.y,a.location.z+a.dimensions.z)
      val b_max = new PhysicsVector(b.location.x+b.dimensions.x,b.location.y+b.dimensions.y,b.location.z+b.dimensions.z)

      if((a.location.x>=b.location.x && a.location.x < b_max.x || a_max.x>=b.location.x && a_max.x<b_max.x )|| (b.location.x>=a.location.x && b.location.x < a_max.x || b_max.x>=a.location.x && b_max.x<a_max.x)){
        if((a.location.y>=b.location.y && a.location.y < b_max.y || a_max.y>=b.location.y && a_max.y<b_max.y )|| (b.location.y>=a.location.y && b.location.y < a_max.y || b_max.y>=a.location.y && b_max.y<a_max.y)){
          if((a.location.z>=b.location.z && a.location.z < b_max.z || a_max.z>=b.location.z && a_max.z<b_max.z )|| (b.location.z>=a.location.z && b.location.z < a_max.z || b_max.z>=a.location.z && b_max.z<a_max.z)){
            return true
          }
        }
      }
      false


   */

  /*
      val a_cen = new PhysicsVector((2*a.location.x+a.dimensions.x)/2,(2*a.location.y+a.dimensions.y)/2,(2*a.location.z+a.dimensions.z)/2)
      val b_cen = new PhysicsVector((2*b.location.x+b.dimensions.x)/2,(2*b.location.y+b.dimensions.y)/2,(2*b.location.z+b.dimensions.z)/2)

      if((a_cen.x-b_cen.x).abs < a.dimensions.x/2 + b.dimensions.x/2){
        if((a_cen.y-b_cen.y).abs < a.dimensions.y/2 + b.dimensions.y/2){
          if((a_cen.z-b_cen.z).abs < a.dimensions.z/2 + b.dimensions.z/2){
            return true
          }
        }
      }
      false

  */
}

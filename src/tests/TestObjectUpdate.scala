package tests
import org.scalatest._
import physics.{PhysicsEngine, PhysicsVector}
import physics.objects.{DynamicObject, GameObject}

class TestObjectUpdate extends FunSuite {
  test("object update check"){
    val a=new DynamicObject(new PhysicsVector(0,0,0),new PhysicsVector(3,4,5))
    val b=new DynamicObject(new PhysicsVector(0,0,3),new PhysicsVector(3,4,5))

    a.velocity = new PhysicsVector(3,4,5)
    b.velocity = new PhysicsVector(1,2,3)

    PhysicsEngine.updateObject(a,5.0,2.0)
    assert(a.velocity.x==3)
    assert(a.velocity.y==4)
    assert(a.velocity.z == 0.0)
    assert(a.location.x == 15)
    assert(a.location.y == 20)
    assert(a.location.z == 0)

    PhysicsEngine.updateObject(b,2.0,1.0)
    assert(b.velocity.z == 1.0)
    assert(b.location.x == 2)
    assert(b.location.y == 4)
    assert(b.location.z == 5)


  }


}


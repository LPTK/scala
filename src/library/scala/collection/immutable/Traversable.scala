package scala.collection.immutable

import generic._

/** A subtrait of Traversable in package collection which represents traversables
 *  that cannot be mutated.
 *  !!! todo: revise equality
 *  @author  Matthias Zenger
 *  @autor   Martin Odersky
 *  @version 2.8
 */
trait Traversable[+A] extends collection.Traversable[A] with TraversableTemplate[A, Traversable[A]] with Immutable { self =>
  override protected[this] def newBuilder = Traversable.newBuilder
  override def traversableBuilder[B]: Builder[B, Traversable[B], Any] = Traversable.newBuilder[B]
}

/* A factory object for the trait `Traversable` */
object Traversable extends TraversableFactory[Traversable] {
  type Coll = Traversable[_]
  implicit def builderFactory[A]: BuilderFactory[A, Traversable[A], Coll] = new BuilderFactory[A, Traversable[A], Coll] { def apply(from: Coll) = from.traversableBuilder[A] }
  def newBuilder[A]: Builder[A, Traversable[A], Any] = new mutable.ListBuffer
}



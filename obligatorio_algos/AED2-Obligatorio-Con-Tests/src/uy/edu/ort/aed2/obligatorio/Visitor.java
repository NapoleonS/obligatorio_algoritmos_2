package uy.edu.ort.aed2.obligatorio;

public interface Visitor<A> {
	A visitar(Object o);
}
package 缓存.抄的;

public interface Computable<A, V> {
	V compute( final A args) throws InterruptedException;

}

package foundation;

public interface RefCounted
{
	void incRefs();
	void decRefs();
	int  getRefs();
}

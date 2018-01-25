package pub.j2ee.ct.chapter3;

public class BiCluster {

	public BiCluster left;
	public BiCluster rigtht;
	public double[] vec;
	public int id;
	public double distance;
	
	public BiCluster(BiCluster left, BiCluster rigtht, double[] vec, int id,
			double distance) {
		super();
		this.left = left;
		this.rigtht = rigtht;
		this.vec = vec;
		this.id = id;
		this.distance = distance;
	}
}

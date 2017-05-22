
public interface Frontier {
	public void add(Location location);
	public Location next();
	public boolean hasNext();
}

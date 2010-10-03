package partuzabook.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UtilsSet {

	private static final long serialVersionUID = 1L;

	public UtilsSet() {
		
	}

	public Set subSet(Set from, int count) {
		Set to = new HashSet();
		Iterator it = to.iterator();
		while (it.hasNext() && count > 0) {
			to.add(it.next());
			count--;
		}
		return to;
	}
}

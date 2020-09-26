package prog08;

import prog02.PhoneDirectory;
import java.util.*;

public class PDMap extends AbstractMap<String, String> {
  PhoneDirectory pd;
  int size = 0;

  public PDMap (PhoneDirectory pd) { this.pd = pd; }

  public int size () { return size; }

  public boolean containsKey (Object key) {
    return pd.lookupEntry((String) key) != null;
  }

  public String get (Object key) {
    return pd.lookupEntry((String) key);
  }

  public boolean isEmpty () { return size == 0; }

  public String put (String key, String value) {
    String old = pd.addOrChangeEntry(key, value);
    if (old == null)
      size++;
    return old;
  }

  public String remove (String key) {
    String old = pd.removeEntry(key);
    if (old != null)
      size--;
    return old;
  }

  public Set<Map.Entry<String, String>> entrySet () { return null; }
}

                                     
  

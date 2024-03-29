package Twenty.DaySeven;

import java.util.*;

public class Bag {
    String color;
    String rule;

    List<Bag> parents;
    HashMap<Bag, Integer> children;

    public Bag(String bagString)
    {
        color = bagString.substring(0, bagString.indexOf(" bags")).trim();
        rule = bagString.substring(bagString.indexOf("contain ")+7);
        parents = new ArrayList();
        children = new HashMap();
    }

    Set<Bag> findRoots()
    {
        Set<Bag> roots = new HashSet();

        for(Bag p : parents)
        {
            if(p.parents.isEmpty())
                roots.add(p);
            else
            {
                roots.add(p);
                roots.addAll(p.findRoots());
            }
        }
        return roots;
    }

    List<Bag> findChildren()
    {
        List<Bag> descendants = new ArrayList();

        for (Map.Entry<Bag, Integer> bagIntegerEntry : children.entrySet()) {
            for (int i = 0; i < bagIntegerEntry.getValue(); i++) {
                descendants.add(bagIntegerEntry.getKey());
                descendants.addAll(bagIntegerEntry.getKey().findChildren());
            }
        }
        return descendants;
    }

    @Override
    public String toString()
    {
        return color;
    }
}

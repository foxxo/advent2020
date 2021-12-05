package TwentyOne.DayFive;

import java.util.Objects;

public class Site {
    int x, y, activity;

    public Site(int x, int y)
    {
        this.x = x;
        this.y = y;
        activity = 1;
    }

    public Site(String s) {
        x = Integer.parseInt(s.substring(0, s.indexOf(",")));
        y = Integer.parseInt(s.substring(s.indexOf(",")+1));
        activity = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return x == site.x &&
                y == site.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

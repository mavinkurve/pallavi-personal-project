
import java.util.Comparator;

class PersonComparator implements Comparator<Person>
{
    @Override
    public int compare(Person o1, Person o2) {
        if (o1.h == o2.h)
            return (o1.k - o2.k);
       else
           return o2.h - o1.h;
    }
}

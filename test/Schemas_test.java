import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class Schemas_test {

    @Test
    public void emptyConstructorTest(){
        Schemas s = new Schemas();

        assertTrue(s.isEmpty());
    }

    @Test
    public void constructorTest(){
        Schemas s = new Schemas(5);

        assertEquals(5, s.size());
    }

    @Test
    public void addThenGetElementTest(){
        Schemas s = new Schemas();

        Schema schema = new Schema("test", "test: test");
        s.add(schema);

        assertEquals(schema, s.get(0));
    }

    @Test
    public void getEmptyTest(){
        Schemas s = new Schemas();

        s.get(0);
    }

    @Test
    public void filterElementsTest(){
        Schemas s = new Schemas();

        Schema schema1 = new Schema("test1", "first");
        Schema schema2 = new Schema("test2", "second");

        s.add(schema1);
        s.add(schema2);

        s.filter(p -> p.name().matches("test1"));

        assertEquals(schema1, s.get(0));

        s.filter(p -> p.name().matches("test3"));

        //No more element
        try{
            s.get(0);
        } catch(NullPointerException e) {
            assertTrue(true);
        }
    }
}

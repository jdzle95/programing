package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueueDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);

        for (int i = 0; i < arb.capacity(); i++) {
            arb.enqueue(i);
        }

        for (int i = 0; i < arb.capacity() / 2; i++) {
            assertEquals((int) arb.dequeue(), i);
        }

        arb.enqueue(18);
        assertEquals((int) arb.peek(), 2);
    }


    @Test
    public void testPeek() {
        BoundedQueue<Integer> arb = new ArrayRingBuffer<>(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }

        for (int i = 0; i < 5; i += 1) {
            arb.dequeue();
        }

        assertEquals((int) arb.peek(), 5);
    }

    @Test
    public void testIsFull() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }

        assertTrue(arb.isFull());
    }


    @Test
    public void testIsEmpty() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.dequeue();
        }

        assertTrue(arb.isEmpty());
    }

    @Test
    public void testPeek1() {
        BoundedQueue<Integer> arb = new ArrayRingBuffer<>(10);

        for (int i = 0; i < arb.capacity(); i += 1) {
            arb.enqueue(i);
        }
        int expected = 0;
        for (int i : arb) {
            assertEquals((int) expected, (int) i);
            expected++;
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 

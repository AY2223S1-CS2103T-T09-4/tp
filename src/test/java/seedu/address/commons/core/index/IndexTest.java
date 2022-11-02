package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IndexTest {

    @Test
    public void createOneBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(0));

        // check equality using the same base
        assertEquals(1, Index.fromOneBased(1).getOneBased());
        assertEquals(5, Index.fromOneBased(5).getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, Index.fromOneBased(1).getZeroBased());
        assertEquals(4, Index.fromOneBased(5).getZeroBased());
    }

    @Test
    public void createZeroBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-1));

        // check equality using the same base
        assertEquals(0, Index.fromZeroBased(0).getZeroBased());
        assertEquals(5, Index.fromZeroBased(5).getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, Index.fromZeroBased(0).getOneBased());
        assertEquals(6, Index.fromZeroBased(5).getOneBased());
    }

    @Test
    public void compareTo() {
        final Index fifthStudentIndex = Index.fromOneBased(5);
        final Index fourthStudentIndex = Index.fromOneBased(4);

        assertTrue(fifthStudentIndex.compareTo(fifthStudentIndex) == 0);

        assertTrue(fifthStudentIndex.compareTo(fourthStudentIndex) > 0);

        assertTrue(fourthStudentIndex.compareTo(fifthStudentIndex) < 0);

        assertFalse(fourthStudentIndex.compareTo(fifthStudentIndex) == 0);
    }

    @Test
    public void equals() {
        final Index fifthStudentIndex = Index.fromOneBased(5);

        // same values -> returns true
        assertTrue(fifthStudentIndex.equals(Index.fromOneBased(5)));
        assertTrue(fifthStudentIndex.equals(Index.fromZeroBased(4)));

        // same object -> returns true
        assertTrue(fifthStudentIndex.equals(fifthStudentIndex));

        // null -> returns false
        assertFalse(fifthStudentIndex.equals(null));

        // different types -> returns false
        assertFalse(fifthStudentIndex.equals(5.0f));

        // different index -> returns false
        assertFalse(fifthStudentIndex.equals(Index.fromOneBased(1)));
    }
}

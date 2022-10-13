package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NokPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NokPhone(null));
    }

    @Test
    public void constructor_invalidNokPhone_throwsIllegalArgumentException() {
        String invalidNokPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new NokPhone(invalidNokPhone));
    }

    @Test
    public void isValidNokPhone() {
        // null next of kin phone number
        assertThrows(NullPointerException.class, () -> NokPhone.isValidNokPhone(null));


        // invalid phone numbers
        assertFalse(NokPhone.isValidNokPhone("")); // empty string
        assertFalse(NokPhone.isValidNokPhone(" ")); // spaces only
        assertFalse(NokPhone.isValidNokPhone("91")); // not 8 numbers
        assertFalse(NokPhone.isValidNokPhone("phone")); // non-numeric
        assertFalse(NokPhone.isValidNokPhone("9011p041")); // alphabets within digits
        assertFalse(NokPhone.isValidNokPhone("9312 1534")); // spaces within digits
        assertFalse(NokPhone.isValidNokPhone("13121534")); // not starting with 6, 8 or 9

        // valid phone numbers
        assertTrue(NokPhone.isValidNokPhone("93121534"));
        assertTrue(NokPhone.isValidNokPhone("63121534"));
        assertTrue(NokPhone.isValidNokPhone("83121534"));
    }
}



import exceptions.InvalidArgument;
import members.MemberArchive;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.lang.reflect.Member;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestMemberArchive {

    /*
    private static MemberArchive archive;

    @BeforeAll
    static void setup() {
        archive = new MemberArchive();
    }

     */
    @Test
    public void testCheckMembers() {
        assertThrows(IllegalArgumentException.class, () -> {
            MemberArchive archive = new MemberArchive();
            archive.checkMembers3(null);
        });
    }

    @Test
    public void testNewMember() {
        assertThrows(InvalidArgument.class, () -> {
            MemberArchive archive = new MemberArchive();
            archive.newMember(null, null);
        });
    }

    @Test
    public void testFindMember() {
        assertThrows(InvalidArgument.class, () -> {
            MemberArchive archive = new MemberArchive();
            archive.getMember(-1);
        });
    }


}

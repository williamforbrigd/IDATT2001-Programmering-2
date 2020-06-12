import members.BasicMember;
import members.BonusMember;
import members.Personals;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TestBonusMember {
    @Test
    public void testInvalidParametersInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            BonusMember member = new BasicMember(0, null, null);
        });
    }

    @Test
    public void testFindQualificationPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            BonusMember member = new BasicMember(10, new Personals("as","asdf", "sad", "dad"), LocalDate.of(10,10,10));
            member.findQualificationPoints(null);
        });
    }

    @Test
    public void testOkPassword() {
        assertThrows(IllegalArgumentException.class, () -> {
            BonusMember member = new BasicMember(10, new Personals("as","asdf", "sad", "dad"), LocalDate.of(10,10,10));
            member.okPassword("");
        }); 
    }
}

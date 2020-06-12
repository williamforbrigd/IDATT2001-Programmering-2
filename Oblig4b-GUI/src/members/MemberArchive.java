package members;

import exceptions.InvalidArgument;

import javax.swing.plaf.SliderUI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class MemberArchive {
    private static final int SILVER_LIMIT = 25000;
    private static final int GOLD_LIMIT = 75000;
    private static final Random RANDOM_NUMBER = new Random();
    private ArrayList<BonusMember> members;


    public MemberArchive() {
        members = new ArrayList<>();
    }

    public ArrayList<BonusMember> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<BonusMember> members) {
        this.members = members;
    }

    /**
     * Creates a new member.
     * @param personals the personals
     * @param enrolledDate the enrolled date.
     * @return the member number of the new member or -1 if it could not find an available member number.
     * @throws InvalidArgument if the personals or enrolleddate is null or is not valid.
     */

    public int newMember(Personals personals, LocalDate enrolledDate) throws InvalidArgument {
        if(personals == null || enrolledDate == null) {
            throw new InvalidArgument("One of the input parameters are invalid.");
        }
        if(findAvailableNo() != -1) {
            members.add(new BasicMember(findAvailableNo(), personals, enrolledDate));
            return findAvailableNo();
        }
        return -1;
    }

    public BonusMember newMember(Personals personals, LocalDate enrolledDate, int points) throws InvalidArgument {
        if(personals == null || enrolledDate == null || points < 0) {
            throw new InvalidArgument("One of the input parameters are invalid.");
        }
        if(findAvailableNo() != -1) {
            BonusMember member = new BasicMember(findAvailableNo(), personals, enrolledDate, points);
            members.add(member);
            return member;
        }
        return null;
    }


    public BonusMember getMember(Personals personals) {
        if(personals != null) {
            for(int i = 0; i < members.size(); i++) {
                if(members.get(i).getPersonals().equals(personals)) {
                    return members.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Gets a member by its member number.
     * @param memberNo the member number of the member.
     * @return the member is the member number is valid.
     * @throws InvalidArgument if the membernumber is not valid. This is a custom made exception that
     * inherits IllegalArgumentException.
     */

    public BonusMember getMember(int memberNo) throws InvalidArgument {
        if (memberNo <= 0) {
            throw new InvalidArgument("The member number cannot be negative");
        }
        BonusMember member = members.stream().filter(m -> m.getMemberNo() == memberNo).findAny().get();
        if(member != null) {
            return member;
        }
        return null;
    }

    public int findPoints(int memberNo, String password) {
        if(memberNo > 0 && password != null && !password.equals("")) {
            for(int i = 0; i < members.size(); i++) {
                if(members.get(i).getMemberNo() == memberNo) {
                    return members.get(i).getPoints();
                }
            }
        }
        return -1;
    }

    public boolean registerPoints(int memberNo, int points) {
        if(memberNo > 0) {
            for(int i = 0; i < members.size(); i++) {
                if (members.get(i).getMemberNo() == memberNo) {
                    members.get(i).registerPoints(points);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean subtractPoints(int memberNo, int points) {
        if(memberNo > 0) {
            for(int i = 0; i < members.size(); i++) {
                if (members.get(i).getMemberNo() == memberNo) {
                    members.get(i).subtractPoints(points);
                    return true;
                }
            }
        }
        return false;
    }

    public int findAvailableNo() {
        int memberNo = 1+RANDOM_NUMBER.nextInt(1000);
        if(members.size() == 0) {
            return memberNo;
        } else {
            for(int i = 0; i < members.size(); i++) {
                if(members.get(i).getMemberNo() != memberNo) {
                    return memberNo;
                }
            }
        }
        return -1;
    }

    /**
     * Method that checks the members for upgrade.
     * @param time which is the time compared to the enrolled date.
     * @throws InvalidArgument if the time parameter is invalid or null, the exception will be
     * thrown.
     */

    public void checkMembers3(LocalDate time) throws InvalidArgument {
        if(time == null) {
            throw new InvalidArgument("The time parameter is null.");
        }
        members.forEach(member -> {
            if(member.findQualificationPoints(time) >= SILVER_LIMIT && member.findQualificationPoints(time) < GOLD_LIMIT) {
                if(member instanceof BasicMember || member instanceof GoldMember) {
                    members.set(members.indexOf(member), new SilverMember(member.getMemberNo(), member.getPersonals(), member.getEnrolledDate(), member.getPoints()));
                }
            } else if(member.findQualificationPoints(time) >= GOLD_LIMIT) {
                if(member instanceof BasicMember || member instanceof SilverMember) {
                    members.set(members.indexOf(member), new GoldMember(member.getMemberNo(), member.getPersonals(), member.getEnrolledDate(), member.getPoints()));
                }
            } else {
                members.set(members.indexOf(member), new BasicMember(member.getMemberNo(), member.getPersonals(), member.getEnrolledDate(), member.getPoints()));
            }
        });
    }

    public String toString() {
        String println = "Total Members: " + members.size() + "\n";
        for(BonusMember member : members) {
            println += member.toString() + "\n";
        }
        return println;
    }


    
}

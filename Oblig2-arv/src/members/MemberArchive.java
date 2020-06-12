package members;

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

    public int newMember(Personals personals, LocalDate enrolledDate) {
        if(personals != null && enrolledDate != null) {
            if(findAvailableNo() != -1) {
                members.add(new BasicMember(findAvailableNo(), personals, enrolledDate));
                return findAvailableNo();
            }
        }
        return -1;
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

    private int findAvailableNo() {
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

    public void checkMembers2(LocalDate time) {
        if(time != null) {
            for(int i = 0; i < members.size(); i++) {
                BonusMember member = members.get(i);
                if(member.findQualificationPoints(time) >= SILVER_LIMIT && members.get(i).findQualificationPoints(time) <= GOLD_LIMIT) {
                    if(member instanceof BasicMember) {
                        members.set(members.indexOf(member), new SilverMember(member.getMemberNo(), member.getPersonals(), member.getEnrolledDate(), member.getPoints()));
                    }
                } else if(member.findQualificationPoints(time) >= GOLD_LIMIT) {
                    if(member instanceof BasicMember || member instanceof SilverMember) {
                        members.set(members.indexOf(member), new GoldMember(member.getMemberNo(), member.getPersonals(), member.getEnrolledDate(), member.getPoints()));
                    }
                } else {
                    if(member instanceof SilverMember || member instanceof GoldMember) {
                        members.set(members.indexOf(member), new BasicMember(member.getMemberNo(), member.getPersonals(), member.getEnrolledDate(), member.getPoints()));
                    }
                }
            }
        }
    }

    public void checkMembers3(LocalDate time) {
        if(time != null) {
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
    }

    /*
    public void checkMembers(LocalDate time) {
        members.stream()
                .filter(member -> member instanceof BasicMember)
                .filter(member -> member.findQualificationPoints(time) >= SILVER_LIMIT && member.findQualificationPoints(time) < GOLD_LIMIT)
                .forEach(s -> members.set(members.indexOf(s), new SilverMember(s.getMemberNo(), s.getPersonals(), s.getEnrolledDate(), s.getPoints())));

        members.stream()
                .filter(member -> member instanceof BasicMember || member instanceof SilverMember)
                .filter(member -> member.findQualificationPoints(time) >= GOLD_LIMIT)
                .forEach(g -> members.set(members.indexOf(g), new GoldMember(g.getMemberNo(), g.getPersonals(), g.getEnrolledDate(), g.getPoints())));
    }

     */

    public String toString() {
        String println = "Total Members: " + members.size() + "\n";
        for(BonusMember member : members) {
            println += member.toString() + "\n";
        }
        return println;
    }


    
}

package project_classes;

import java.util.ArrayList;

public class Group {
    
    private String groupId;
    private int groupSize;
    private ArrayList<Student> students;
    private ArrayList<TimeSlot> assignedTimeSlots;
    
    public Group(String groupId) {
        this.groupId = groupId;
        this.groupSize = 0;
        this.students = new ArrayList<>();
        this.assignedTimeSlots = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }


    public int getGroupSize() {
        return students.size();
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }


    public ArrayList<TimeSlot> getAssignedTimeSlots() {
        return new ArrayList<>(assignedTimeSlots);
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            groupSize = students.size();
        } else {
            System.out.println("Student " + student.getId() + " is already in group " + groupId);
        }
    }


    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            groupSize = students.size();
        } else {
            System.out.println("Student " + student.getId() + " not found in group " + groupId);
        }
    }


    public void assignTimeSlot(TimeSlot timeSlot) {
        if (!assignedTimeSlots.contains(timeSlot)) {
            assignedTimeSlots.add(timeSlot);
        } else {
            System.out.println("TimeSlot already assigned to group " + groupId);
        }
    }


    public void removeTimeSlot(TimeSlot timeSlot) {
        if (assignedTimeSlots.contains(timeSlot)) {
            assignedTimeSlots.remove(timeSlot);
        } else {
            System.out.println("TimeSlot not found in group " + groupId);
        }
    }


    public boolean hasSchedulingConflicts() {
        // Simple conflict detection - you can enhance this
        for (int i = 0; i < assignedTimeSlots.size(); i++) {
            for (int j = i + 1; j < assignedTimeSlots.size(); j++) {
                TimeSlot slot1 = assignedTimeSlots.get(i);
                TimeSlot slot2 = assignedTimeSlots.get(j);
                
                if (slot1.getDayOfWeek().equals(slot2.getDayOfWeek())) {
                    // Check for time overlap
                    if (slot1.getStartTime().isBefore(slot2.getEndTime()) && 
                        slot2.getStartTime().isBefore(slot1.getEndTime())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Group ID: " + groupId +
               "\nProgramme: " + programme +
               "\nYear: " + yearOfStudy + ", Semester: " + semester +
               "\nGroup Size: " + groupSize +
               "\nNumber of Students: " + students.size() +
               "\nAssigned Time Slots: " + assignedTimeSlots.size() +
               "\nModules: " + getGroupModules().toString();
    }
}
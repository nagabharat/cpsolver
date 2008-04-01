package net.sf.cpsolver.exam.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a course or a section (or any other group of students that is 
 * associated with an exam). This entity is not used
 * for examination timetabling, but it may be important for reports since
 * students are usually enrolled to sections and/or courses and an exam
 * can be offered for a set of courses/sections. 
 * <br><br>
 * The relations between course/section and exams, students and instructors are
 * bidirectional, see {@link Exam#getOwners()}, 
 * {@link ExamStudent#getOwners()}, and 
 * {@link ExamInstructor#getOwners()}. 
 * <br><br>
 * 
 * @version
 * ExamTT 1.1 (Examination Timetabling)<br>
 * Copyright (C) 2008 Tomas Muller<br>
 * <a href="mailto:muller@unitime.org">muller@unitime.org</a><br>
 * Lazenska 391, 76314 Zlin, Czech Republic<br>
 * <br>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <br><br>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <br><br>
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
public class ExamOwner implements Comparable {
    private long iId;
    private String iName;
    private Exam iExam;
    private HashSet iStudents = new HashSet();
    private HashSet iInstructors = new HashSet();
    
    /**
     * Constructor.
     * @param exam an exam for this course/section
     * @param id unique id
     * @param name course/section name
     */
    public ExamOwner(Exam exam, long id, String name) {
        iExam = exam;
        iId = id;
        iName = name;
    }
    
    /**
     * Unique identifier
     */
    public long getId() {
        return iId;
    }
    
    /**
     * Course/section name
     */
    public String getName() {
        return iName;
    }

    /**
     * An exam for this course/section 
     */
    public Exam getExam() {
        return iExam;
    }
    
    /**
     * List of students that are enrolled into this section/course
     * @return set of {@link ExamStudent}
     */
    public Set getStudents() {
        return iStudents;
    }
    
    /**
     * List of instructors that are enrolled into this section/course
     * @return set of {@link ExamInstructor} 
     */
    public Set getIntructors() {
        return iInstructors;
    }

    /**
     * String representation -- course/section name
     */
    public String toString() {
        return iName;
    }
    
    /** Hash code */
    public int hashCode() {
        return (int)(iId ^ (iId >>> 32));
    }
    
    /** Compare two exam owners for equality */
    public boolean equals(Object o) {
        if (o==null || !(o instanceof ExamOwner)) return false;
        return getId() == ((ExamOwner)o).getId();
    }
    
    /** Compare two exam owners by name */
    public int compareTo(Object o) {
        ExamOwner owner = (ExamOwner)o;
        if (!getName().equals(owner.getName())) return getName().compareTo(owner.getName());
        return Double.compare(getId(),owner.getId());
    }
}
package Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Service layer is mainly responsible for business logic.

@Service   // or @Component
public class StudentService { // After applying autowired in StudentController file, we need to know that "StudentService" class is part of the bean.

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
         if(studentOptional.isPresent()){
             throw new IllegalStateException("email taken");
         }
         studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "student with id"+ studentId + " doesn't exists"
            );
        }
    }

    @Transactional  // with this annotation, we dont need to add in "studentRepository" file
    public void updateStudent(Long studentId, String name, String email) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalStateException(
                    "student with id "+ studentId + " doesn't exist"
            ));
     if( name != null &&  name.length()>0 &&
    !Objects.equals(student.getName(),name)){
         student.setName(name);
        }
       if(email != null &&
       email.length()>0 && !Objects.equals(student.getEmail(),email)){
           Optional<Student> studentOptional = studentRepository
                   .findStudentByEmail(email);
           if(studentOptional.isPresent()){
               throw new IllegalStateException("email taken");
           }
           student.setEmail(email);
       }
    }
}

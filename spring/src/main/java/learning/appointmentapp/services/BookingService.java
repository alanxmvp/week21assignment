package learning.appointmentapp.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning.appointmentapp.entities.Appointment;
import learning.appointmentapp.entities.Employee;
import learning.appointmentapp.repositories.AppointmentRepository;
import learning.appointmentapp.repositories.EmployeeRepository;

@Service
public class BookingService {
  @Autowired
  AppointmentRepository appointmentRepo;

  @Autowired
  EmployeeRepository employeeRepo;

  public List<LocalDateTime> checkAppointment(Employee employee) {
    List<Appointment> appointments = appointmentRepo.findByEmployee(employee);

    ArrayList<LocalDateTime> results = new ArrayList<LocalDateTime>();

    for (Appointment appointment : appointments) {
      results.add(appointment.getTimeslot());
    }

    return results;
  }

  public Appointment bookAppointment(LocalDateTime timeslot, Employee employee) {
    LocalDateTime startTime = timeslot.minusHours(2);
    LocalDateTime endTime = timeslot.plusHours(2);

    List<Appointment> results = appointmentRepo.findByTimeslotBetween(startTime, endTime);

    if (results.size() == 0) {
      Appointment appointment = new Appointment();
      appointment.setEmployee(employee);
      appointment.setTimeslot(timeslot);
      appointmentRepo.save(appointment);
      return appointment;
    } else {
      return null;
    }
  }

}
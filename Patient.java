public class Patient extends User {

	private String birth;
	private int contactNumber;
	private String email;
	private String bloodType;

	public Patient() {
		// TODO - implement Patient.Patient
		throw new UnsupportedOperationException();
	}

	public void updatePersonalInformation() {
		// TODO - implement Patient.updatePersonalInformation
		throw new UnsupportedOperationException();
	}

	public void viewAvailableAppointmentSlots() {
		// TODO - implement Patient.viewAvailableAppointmentSlots
		throw new UnsupportedOperationException();
	}

	public void viewMedicalRecord() {
		// TODO - implement Patient.viewMedicalRecord
		throw new UnsupportedOperationException();
	}

	public void scheduleAppointment() {
		// TODO - implement Patient.scheduleAppointment
		throw new UnsupportedOperationException();
	}

	public void rescheduleAppointment() {
		// TODO - implement Patient.rescheduleAppointment
		throw new UnsupportedOperationException();
	}

	public void cancelAppointment() {
		// TODO - implement Patient.cancelAppointment
		throw new UnsupportedOperationException();
	}

	public void viewScheduledAppointment() {
		// TODO - implement Patient.viewScheduledAppointment
		throw new UnsupportedOperationException();
	}

	public void viewPastAppointmentOutcomeRecord() {
		// TODO - implement Patient.viewPastAppointmentOutcomeRecord
		throw new UnsupportedOperationException();
	}

	private int getContactNumber() {
		return this.contactNumber;
	}

	/**
	 * 
	 * @param newcontactNumber
	 */
	private void setContactNumber(int newcontactNumber) {
		this.contactNumber = newcontactNumber;
	}

	private String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param newEmail
	 */
	private void setEmail(String newEmail) {
		this.email = newEmail;
	}

}
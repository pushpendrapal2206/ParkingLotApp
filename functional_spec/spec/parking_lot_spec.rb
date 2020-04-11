require 'spec_helper'

RSpec.describe 'Parking Lot', type: :aruba do
  let(:pty) { PTY.spawn('parking_lot') }
  it "has aruba set up" do
    command = run "echo 'hello world'"
    stop_all_commands
    expect(command.output).to eq("hello world\n")
  end

  describe "individual scenarios" do
    before(:each) do
      run_command(pty, "create_parking_lot 3\n")
    end

    it "can create a parking lot", :sample => true do
      expect(fetch_stdout(pty)).to end_with("Created parking lot with 3 slots\n")
    end

    it "can park a car" do
      run_command(pty, "park KA-01-HH-3141\n")
      expect(fetch_stdout(pty)).to end_with("Allocated slot number: 1\n")
    end

    it "can unpark a car" do
      run_command(pty, "park KA-01-HH-3141\n")
      run_command(pty, "leave KA-01-HH-3141 4\n")
      expect(fetch_stdout(pty)).to end_with("Registration number KA-01-HH-3141 with Slot Number 1 is free with Charge 30\n")
    end

    it "can report status" do
      run_command(pty, "park KA-01-HH-1234\n")
      run_command(pty, "park KA-01-HH-3141\n")
      run_command(pty, "park KA-01-HH-9999\n")
      run_command(pty, "status\n")
      expect(fetch_stdout(pty)).to end_with(<<-EOTXT
Slot No.\tRegistration No.
1\t\tKA-01-HH-1234\t\t
2\t\tKA-01-HH-3141\t\t
3\t\tKA-01-HH-9999\t\t
EOTXT
)
    end

    it "can give error for duplicate parking of a car" do
      run_command(pty, "park KA-01-HH-3141\n")
      run_command(pty, "park KA-01-HH-3141\n")
      expect(fetch_stdout(pty)).to end_with("Car is already parked\n")
    end

    it "can give error for unparking for an invalid car" do
      run_command(pty, "leave KA-01-HH-3141 3\n")
      expect(fetch_stdout(pty)).to end_with("Registration number KA-01-HH-3141 not found\n")
    end

    it "can give error for invalid commands" do
      run_command(pty, "abc\n")
      expect(fetch_stdout(pty)).to end_with("Invalid command\n")
    end

    it "can give error when parking lot is full" do
      run_command(pty, "park KA-01-HH-1234\n")
      run_command(pty, "park KA-01-HH-3141\n")
      run_command(pty, "park KA-01-HH-9999\n")
      run_command(pty, "park KA-01-HH-3142\n")
      expect(fetch_stdout(pty)).to end_with("Sorry, parking lot is full\n")
    end

    it "can give error when parking car with duplicate registration number" do
      run_command(pty, "park KA-01-HH-1234\n")
      run_command(pty, "park KA-01-HH-1234\n")
      expect(fetch_stdout(pty)).to end_with("Car is already parked\n")
    end
  end
end

package test;

public class FizzBuzz {

    public byte[] fizzBuzz(int number) throws IllegalAccessException {

        String result = "";

        if (number % 3 == 0) {
            result += "Fizz";
        }
        if (number % 5 == 0) {
            result += "Buzz";
        }

        if (result.isEmpty()) {
            throw new IllegalAccessException("Number is not divided to 3 or 5");
        }

        return result.getBytes();

    }


}

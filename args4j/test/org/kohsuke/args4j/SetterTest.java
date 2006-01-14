package org.kohsuke.args4j;

public class SetterTest extends Args4JTest {
    @Override
    public Object getTestObject() {
        return new Setter();
    }

    public void testSettingStringNoValues() {
        Setter bo = (Setter)testObject;
        args = new String[]{};
        try {
            parser.parseArgument(args);
            assertTrue("Default value set.", "default".equals(bo.str));
        } catch (CmdLineException e) {
            fail("Call without parameters is valid!");
        }
    }

    public void testSettingString() {
        Setter bo = (Setter)testObject;
        args = new String[]{"-str","test"};
        try {
            parser.parseArgument(args);
            assertTrue("Given value set.", "TEST".equals(bo.str));
        } catch (CmdLineException e) {
            fail("Setting a string value should be possible");
        }
    }

    public void testSettingUsage() {
        args = new String[]{"-wrong-usage"};
        try {
            parser.parseArgument(args);
            fail("Doesnt detect wrong parameters.");
        } catch (CmdLineException e) {
            String expectedError = "\"-wrong-usage\" is not a valid option";
            String expectedUsage   = " -str VAL : set a string";
            //TODO check the correct usage message - needs return value from parser
            //String usageMessage = parser.printUsage(null).toString();
            String errorMessage = e.getMessage();
            assertTrue("Got wrong error message", errorMessage.startsWith(expectedError));
            //assertTrue("Got wrong usage message", usageMessage.startsWith(expectedUsage));
        }
    }

    public void testMissingParameter() {
        args = new String[]{"-str"};
        try {
            parser.parseArgument(args);
            fail("Should miss one parameter.");
        } catch (CmdLineException e) {
            String expectedError = "Option \"-str\" takes an operand";
            String expectedUsage   = " -str VAL : set a string";
            //TODO check the correct usage message - needs return value from parser
            //String usageMessage = parser.printUsage(null).toString();
            String errorMessage = e.getMessage();
            assertTrue("Got wrong error message", errorMessage.startsWith(expectedError));
            //assertTrue("Got wrong usage message", usageMessage.startsWith(expectedUsage));
        }
    }

}
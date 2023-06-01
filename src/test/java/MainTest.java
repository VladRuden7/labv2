import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.Main;
import org.testng.Assert;
import org.testng.annotations.*;

public class MainTest {
  private static String path;
  private static File file;
  private static String input = "abc def ghi jklm nop qrs tuv wxyz";
  private static String expectedOutput = "abc def ghi nop qrs tuv";
  @BeforeClass
  public static void setup() throws IOException {

    path = "D:\\";
    file = new File(path);
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    writer.write(input);
    writer.close();

  }
  @AfterClass
  public static void delete()
  {
    file.deleteOnExit();
  }



  @Test(groups = {"group1","group2"})
  public void testReadFromFileEmptyPath() {
    String actual = Main.readFromFile("");
    assertNull(actual);
  }

  @Test(groups = "group1")
  public void testReadFromFileNonexistentPath() {
    String actual = Main.readFromFile("nonexistent/path");
    assertNull(actual);
  }

  @Test(groups = "group1")
  public void testSplitWords() {
    String input = "abc def ghi jklm nop qrs tuv wxyz";
    String[] expected = {"abc", "def", "ghi", "jklm", "nop", "qrs", "tuv", "wxyz"};
    String[] actual = Main.splitWords(input);
    assertArrayEquals(actual, expected);
  }

  @Test(groups = "group1")
  public void testSplitWordsWithPunctuation() {
    String input = "abc, def. ghi; jklm? nop! qrs: tuv wxyz.";
    String[] expected = {"abc", "def", "ghi", "jklm", "nop", "qrs", "tuv", "wxyz"};
    String[] actual = Main.splitWords(input);
    assertArrayEquals(actual, expected);
  }

  @Test(groups = "group1")
  public void testSplitWordsWithLongWords() {
    String input = "abc defghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz";
    String[] expected = {"abc", "defghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"};
    String[] actual = Main.splitWords(input);
    assertArrayEquals(actual, expected);
  }

  @Test(groups = "group1")
  public void testResult() {
    String[] input = {"abc", "def", "ghi", "jklm", "nop", "qrs", "tuv", "wxyz"};
    ArrayList<String> expected = new ArrayList<>(Arrays.asList("abc", "def", "ghi", "jklm", "nop", "qrs", "tuv", "wxyz"));
    ArrayList<String> actual = Main.result(input);
    assertEquals(actual, expected);
  }
  @DataProvider(name = "inputData")
  public Object[][] inputData() {
    return new Object[][] {
        { "abc def ghi", Arrays.asList("abc", "def", "ghi") },
        { "jklm nop qrs", Arrays.asList("jklm", "nop", "qrs") },
        { "tuv wxyz", Arrays.asList("tuv", "wxyz") }
    };
  }

  @Test(dataProvider = "inputData",groups = "group2")
  public void testSplitWords(String input, List<String> expectedOutput) {
    List<String> output = List.of(Main.splitWords(input));
    Assert.assertEquals(output, expectedOutput);
  }
  @Test(expectedExceptions = {NullPointerException.class}, groups = "group2")
  public void testReadFromFileWithNullArgument() {
    String path = null;
    String result = Main.readFromFile(path);
    assertNull(result);
  }

}
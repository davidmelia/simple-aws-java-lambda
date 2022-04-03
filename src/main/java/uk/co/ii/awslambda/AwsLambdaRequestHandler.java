package uk.co.ii.awslambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AwsLambdaRequestHandler implements RequestStreamHandler {
  private static final String supported_arm_architectures = "^(aarch64.*|arm64.*)$";
  private static final String supported_x86_architectures = "^(x8664|amd64|ia32e|em64t|x64|x86_64)$";

  static {
    String arch = System.getProperty("os.arch");
    System.out.println("os.arch=" + arch);
    if (arch.matches(supported_x86_architectures)) {
      System.out.println("x86_64");
    } else if (arch.matches(supported_arm_architectures)) {
      System.out.println("arm64");
    }
  }


  public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)));
    try {
      writer.write("OK");
    } finally {
      writer.close();
    }
  }
}

package com.example.sts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.StsException;

@RestController
public class StsController {

    private String roleArn = System.getenv("AWS_ROLE_ARN");

    @GetMapping("/status/tests/sts")
    public ResponseEntity<String> assumeRole() {
        StsClient stsClient = StsClient.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .roleSessionName("demoSession")
                .build();

        AssumeRoleResponse assumeRoleResponse;
        try {
            assumeRoleResponse = stsClient.assumeRole(assumeRoleRequest);
        } catch (StsException e) {
            return ResponseEntity.status(e.statusCode())
                    .body("Error assuming role: " + e.awsErrorDetails().errorCode() + " " + e.awsErrorDetails().errorMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error assuming role: " + e.getMessage());
        }
        return ResponseEntity.ok("OK<br>Assume Role Success! <br>Assume Role ARN: " + assumeRoleResponse.assumedRoleUser().arn());
    }
}




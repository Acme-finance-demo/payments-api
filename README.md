# payments-api

A small payment-authorization service used to exercise the continuous vulnerability
governance flow in `Acme-finance-demo/cursor-governance-demo`.

The code is real but deliberately small. The dependency versions in `pom.xml` are
deliberately old, so a scan has something to find.

```
src/main/java/com/example/payments/
  App.java                 entry point
  PaymentProcessor.java    authorization path, logs every decision
  RefundService.java       refund path, logs failures with a throwable
  AuditLog.java            correlation ids via Log4j ThreadContext
src/main/resources/log4j2.xml   console pattern layout + JSON audit appender
```

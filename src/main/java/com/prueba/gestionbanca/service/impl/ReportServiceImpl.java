package com.prueba.gestionbanca.service.impl;

import com.prueba.gestionbanca.expose.response.BalanceAccountResponse;
import com.prueba.gestionbanca.expose.response.BalanceCreditResponse;
import com.prueba.gestionbanca.expose.response.ProductBalanceResponse;
import com.prueba.gestionbanca.model.Account;
import com.prueba.gestionbanca.model.Client;
import com.prueba.gestionbanca.model.Credit;
import com.prueba.gestionbanca.model.Movements;
import com.prueba.gestionbanca.repository.ClientRepository;
import com.prueba.gestionbanca.service.ReportService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


/**
 * a.
 * <b>Class</b>: ReportServiceImpl <br/>
 *
 * <u>Service Provider</u>: PruebaTest <br/>
 * <u>Developed by</u>: Diego Condezo <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Enero 29, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

  @Autowired
  private ClientRepository clientRepo;

  @SuppressWarnings({"checkstyle:LocalVariableName", "all"})
  @Override
  public byte[] generateReportBalanceProducts(String numberDocument) throws IOException {

    Mono<ProductBalanceResponse> dataReport = findProductByDocumentNumber(numberDocument);

    try (PDDocument document = new PDDocument()) {
      PDPage page = new PDPage();
      document.addPage(page);

      try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
        float fontSize = 12;
        float margin = 50;
        float yPosition = page.getMediaBox().getHeight() - margin;


        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.beginText();
        contentStream.newLineAtOffset(190, yPosition);
        contentStream.showText("Reporte de Saldos de Productos");
        contentStream.endText();
        yPosition -= 20;

        contentStream.setFont(PDType1Font.HELVETICA, 16);
        yPosition -= 20;


        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("CUENTAS:");
        contentStream.endText();
        yPosition -= 20;

        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);

        String type = "Type"; // NOSONAR
        int stype = 30 - type.length(); // NOSONAR

        String card = "Card"; // NOSONAR
        int scard = 40 - card.length(); // NOSONAR

        String number = "Number"; // NOSONAR
        int snumber = 40 - number.length(); // NOSONAR

        String balance = "Balance"; // NOSONAR
        int sbalance = 25 - balance.length(); // NOSONAR

        String formateo = "%-" + stype + "s%-" + scard + "s%-" + snumber + "s%-" + sbalance + "s";
        String cabecera = String.format(formateo, "Type", "Card", "Number", "Balance");
        contentStream.showText(cabecera);


        contentStream.endText();
        yPosition -= 20;

        for (BalanceAccountResponse account : dataReport.block().getAccount()) {
          contentStream.setFont(PDType1Font.HELVETICA, fontSize);  // NOSONAR
          contentStream.beginText();  // NOSONAR
          contentStream.newLineAtOffset(margin, yPosition);  // NOSONAR

          String rtype = account.getType().trim(); // NOSONAR

          int rstype; // NOSONAR
          if (rtype.equals("Cta Corriente")) {  // NOSONAR
            rstype = 30 - rtype.length() + 3; // NOSONAR
          } else { // NOSONAR
            rstype = 30 - rtype.length(); // NOSONAR
          } // NOSONAR

          String rcard = account.getCard().trim(); // NOSONAR
          int rscard = 40 - rcard.length(); // NOSONAR

          String rnumber = account.getNumber().trim(); // NOSONAR
          int rsnumber = 40 - rnumber.length(); // NOSONAR

          String rbalance = String.valueOf(account.getBalance()).trim(); // NOSONAR
          int rsbalance = 25 - rbalance.length(); // NOSONAR

          String fila = String.format("%-" + rstype + "s%-" + rscard + "s%-"
                          + rsnumber + "s%-" + rsbalance + "s",
                  account.getType().trim(), account.getCard().trim(), account.getNumber().trim(),
                        String.valueOf(account.getBalance()).trim());
          contentStream.showText(fila);

          contentStream.endText();
          yPosition -= 20;
        }

        contentStream.setFont(PDType1Font.HELVETICA, 16);
        yPosition -= 20;


        contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("CREDITOS:");
        contentStream.endText();
        yPosition -= 20;

        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);

        String ctype = "Type"; // NOSONAR
        int cstype = 30 - ctype.length(); // NOSONAR

        String ccard = "Card"; // NOSONAR
        int cscard = 40 - ccard.length(); // NOSONAR

        String cnumber = "Number"; // NOSONAR
        int csnumber = 40 - cnumber.length(); // NOSONAR

        String cbalance = "Balance"; // NOSONAR
        int csbalance = 25 - cbalance.length(); // NOSONAR

        String climit = "Limit"; // NOSONAR
        int cslimit = 25 - climit.length(); // NOSONAR

        String formateoCred = "%-" + cstype + "s%-" + cscard + "s%-"
                  + csnumber + "s%-" + csbalance + "s%-" + cslimit + "s";
        String cabeceraCredit = String.format(formateoCred, "Type", "Card", "Number",
                          "Balance", "Limit");
        contentStream.showText(cabeceraCredit);

        contentStream.endText();
        yPosition -= 20;

        for (BalanceCreditResponse account : dataReport.block().getCredit()) {
          contentStream.setFont(PDType1Font.HELVETICA, fontSize);  // NOSONAR
          contentStream.beginText();  // NOSONAR
          contentStream.newLineAtOffset(margin, yPosition);  // NOSONAR

          String rtype = account.getType().trim(); // NOSONAR
          int rstype; // NOSONAR
          if (rtype.equals("Empresarial")) {  // NOSONAR
            rstype = 30 - rtype.length() + 3; // NOSONAR
          } else { // NOSONAR
            rstype = 30 - rtype.length(); // NOSONAR
          } // NOSONAR

          String rcard = account.getCard().trim(); // NOSONAR
          int rscard = 40 - rcard.length(); // NOSONAR

          String rnumber = account.getNumber().trim(); // NOSONAR
          int rsnumber = 40 - rnumber.length(); // NOSONAR

          String rbalance = String.valueOf(account.getBalance()).trim(); // NOSONAR
          int rsbalance = 25 - rbalance.length(); // NOSONAR

          String rlimit = String.valueOf(account.getLimitCredit()).trim(); // NOSONAR
          int rslimit = 25 - rlimit.length(); // NOSONAR

          String fila = String.format("%-" + rstype + "s%-" + rscard + "s%-"
                          + rsnumber + "s%-" + rsbalance + "s%-" + rslimit + "s",
                  account.getType().trim(), account.getCard().trim(), account.getNumber().trim(),
                        String.valueOf(account.getBalance()).trim(),
                        String.valueOf(account.getLimitCredit()).trim());

          contentStream.showText(fila);
          contentStream.endText();
          yPosition -= 20;
        }

        contentStream.setFont(PDType1Font.HELVETICA, 16);
        yPosition -= 40;


        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("Total Saldo de Cuentas: "
                + dataReport.block().getTotalBalanceAccount());
        contentStream.endText();
        yPosition -= 20;

        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("Total Saldo de Credito: "
                + dataReport.block().getTotalBalanceCredit());
        contentStream.endText();
        yPosition -= 20;

        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("Saldo Total: "
                + dataReport.block().getTotalBalance());
        contentStream.endText();
      }

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      document.save(baos);
      return baos.toByteArray();
    }


  }

  @Override
  public byte[] generateReportTotalComissionByProducts(String accountNumber) throws IOException {

    Mono<Client> dataReport = clientRepo.findAccountByNumberWithDataClient(accountNumber);

    try (PDDocument document = new PDDocument()) {
      PDPage page = new PDPage();
      document.addPage(page);

      try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
        float fontSize = 12;
        float margin = 50;
        float yPosition = page.getMediaBox().getHeight() - margin;


        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.beginText();
        contentStream.newLineAtOffset(190, yPosition);
        contentStream.showText("Reporte de Comisiones de Producto");
        contentStream.endText();
        yPosition -= 20;

        contentStream.setFont(PDType1Font.HELVETICA, 16);
        yPosition -= 20;


        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("OPERACIONES");
        contentStream.endText();
        yPosition -= 20;

        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);

        String numero = "Numero"; // NOSONAR
        int snumero = 20 - numero.length(); // NOSONAR

        String tipo = "Tipo"; // NOSONAR
        int stipo = 26 - tipo.length(); // NOSONAR

        String fecha = "Fecha"; // NOSONAR
        int sfecha = 40 - fecha.length(); // NOSONAR

        String monto = "Monto"; // NOSONAR
        int smonto = 16 - monto.length(); // NOSONAR

        String comision = "Comision"; // NOSONAR
        int scomision = 20 - comision.length(); // NOSONAR

        String montoNeto = "MontoNeto"; // NOSONAR
        int smontoNeto = 25 - montoNeto.length(); // NOSONAR

        String formateo = "%-" + snumero + "s%-" + stipo + "s%-" + sfecha + "s%-" + smonto + "s%-" + scomision + "s%-" + smontoNeto + "s";
        String cabecera = String.format(formateo, "Numero", "Tipo", "Fecha", "Monto", "Comision", "MontoNeto");
        contentStream.showText(cabecera);


        contentStream.endText();
        yPosition -= 20;

        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal comission = BigDecimal.ZERO;
        BigDecimal amountNet = BigDecimal.ZERO;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        for (Movements movement :  dataReport.block()
                                    .getProduct()
                                    .getAccount()
                                    .stream()
                                    .flatMap(account -> account.getMovements().stream())
                                    .filter(movement -> movement.getComission() != null)
                                    .collect(Collectors.toList())) {

          contentStream.setFont(PDType1Font.HELVETICA, fontSize);  // NOSONAR
          contentStream.beginText();  // NOSONAR
          contentStream.newLineAtOffset(margin, yPosition);  // NOSONAR

          String fnumber = movement.getNumberOperation().trim(); // NOSONAR
          int fsnumber = 19 - fnumber.length() + 3; // NOSONAR

          String foperation = movement.getOperationType().toString(); // NOSONAR
          int fsoperation; // NOSONAR
          if (foperation.equals("DEPOSITO")) {  // NOSONAR
            fsoperation = 25 - foperation.length() - 2; // NOSONAR
          } else { // NOSONAR
            fsoperation = 25 - foperation.length() - 1; // NOSONAR
          } // NOSONAR

          String fdate = sdf.format(movement.getDateOperation()); // NOSONAR
          int fsdate = 45 - fdate.length(); // NOSONAR

          String fmonto = String.valueOf(movement.getGrossAmount()).trim(); // NOSONAR
          int fsmonto = 18 - fmonto.length(); // NOSONAR

          String fcomission = String.valueOf(movement.getComission()).trim(); // NOSONAR
          int fscomission = 20 - fcomission.length(); // NOSONAR

          String fmontoNeto = String.valueOf(movement.getAmount()).trim(); // NOSONAR
          int fsmontoNeto = 25 - fmontoNeto.length(); // NOSONAR

          String fila = String.format("%-" + fsnumber + "s%-" + fsoperation + "s%-"
                          + fsdate + "s%-" + fsmonto + "s%-" + fscomission + "s%-" + fsmontoNeto +"s",
                  movement.getNumberOperation().trim(), movement.getOperationType().toString(), sdf.format(movement.getDateOperation()),
                  String.valueOf(movement.getGrossAmount()).trim(),  String.valueOf(movement.getComission()).trim(),
                  String.valueOf(movement.getAmount()).trim());
          contentStream.showText(fila);
          contentStream.endText();
          yPosition -= 20;

          amountTotal = amountTotal.add(movement.getGrossAmount());
          comission = comission.add(movement.getComission());
          amountNet = amountNet.add(movement.getAmount());

        }

        contentStream.setFont(PDType1Font.HELVETICA, 16);
        yPosition -= 40;


        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("Total Monto: " + amountTotal.setScale(2, BigDecimal.ROUND_UP));
        contentStream.endText();
        yPosition -= 20;

        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("Total Comision: " + comission.setScale(2, BigDecimal.ROUND_UP));
        contentStream.endText();
        yPosition -= 20;

        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("Total Monto Neto: " + amountNet.setScale(2, BigDecimal.ROUND_UP));
        contentStream.endText();
      }

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      document.save(baos);
      return baos.toByteArray();
    }

  }

  /**
   * a.
   *
   * @param document a.
   * @return findProductByDocumentNumber
   */
  public Mono<ProductBalanceResponse> findProductByDocumentNumber(String document) {
    return clientRepo.findProductsByDocumentNumber(document)
            .flatMap(x -> {
              List<BalanceAccountResponse> balanceAccountLst = x.getProduct().getAccount().stream()
                      .map(account -> new BalanceAccountResponse(account.getType(),
                              account.getCard(),
                              account.getNumber(),
                              account.getBalance()))
                      .collect(Collectors.toList());

              List<BalanceCreditResponse> balanceCreditLst = x.getProduct().getCredit().stream()
                      .map(credit -> new BalanceCreditResponse(credit.getType(),
                              credit.getCard(),
                              credit.getNumber(),
                              credit.getLimitCredit(),
                              credit.getBalance()))
                      .collect(Collectors.toList());

              BigDecimal accountTotal = x.getProduct().getAccount().stream()
                      .map(Account::getBalance)
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

              BigDecimal creditTotal = x.getProduct().getCredit().stream()
                      .map(Credit::getBalance)
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

              return Mono.just(ProductBalanceResponse.builder()
                      .account(balanceAccountLst)
                      .credit(balanceCreditLst)
                      .totalBalanceAccount(accountTotal)
                      .totalBalanceCredit(creditTotal)
                      .totalBalance(accountTotal.add(creditTotal))
                      .build());
            });
  }
}

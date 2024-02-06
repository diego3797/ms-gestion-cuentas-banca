package com.prueba.gestionbanca.expose;

import com.prueba.gestionbanca.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * .
 * <b>Class</b>: ReportController <br/>
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
@RestController
@RequestMapping("/report")
@Slf4j
@RequiredArgsConstructor
public class ReportController {

  @Autowired
  private ReportService reportService;

  /**
   * .
   * GET /report/balanceAllProduct/{numberDocument} :
   * Gets report the balance of all products from client
   * Gets report the balance of all products from client
   *
   * @param numberDocument number document of client (required)
   * @return successful operation (status code 200)
   *         or Invalid ID supplied (status code 400)
   *         or product not found (status code 404)
   */
  @Operation(
          operationId = "getReportBalanceProducts",
          summary = "Gets report the balance of all products from client",
          description = "Gets report the balance of all products from client",
          tags = { "reports" },
          responses = {
              @ApiResponse(responseCode = "200", description = "successful operation"),
              @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
              @ApiResponse(responseCode = "404", description = "product not found")
          }
  )
  @GetMapping(
          value = "/balanceAllProduct/{numberDocument}",
          produces = { "application/PDF" }
  )
  public Mono<ResponseEntity<byte[]>> getReportBalanceProducts(
          @Parameter(name = "numberDocument",
                     description = "number document of client",
                     required = true, in = ParameterIn.PATH)
          @PathVariable("numberDocument") String numberDocument
  ) throws IOException {

    byte[] pdfBytes = reportService.generateReportBalanceProducts(numberDocument);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDispositionFormData("filename", "reportBalanceAllProduct.pdf");

    return Mono.just(new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK));


  }


  /**
   * .
   * GET /report/commissionbyProduct/{accountNumber} :
   * Gets report commission by products from client
   * Gets report commission by products from client
   *
   * @param accountNumber number document of client (required)
   * @return successful operation (status code 200)
   *         or Invalid ID supplied (status code 400)
   *         or product not found (status code 404)
   */
  @Operation(
          operationId = "getReportCommisionByProduct",
          summary = "Gets report commission by products from client",
          description = "Gets report commission by products from client",
          tags = { "reports" },
          responses = {
              @ApiResponse(responseCode = "200", description = "successful operation"),
              @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
              @ApiResponse(responseCode = "404", description = "product not found")
          }
  )
  @RequestMapping(
          method = RequestMethod.GET,
          value = "/commissionbyProduct/{accountNumber}",
          produces = { "application/json", "application/xml" }
  )
  public Mono<ResponseEntity<byte[]>> getReportCommisionByProduct(
          @Parameter(name = "numberDocument",
                     description = "number document of client",
                     required = true, in = ParameterIn.PATH)
          @PathVariable("accountNumber") String accountNumber
  ) throws IOException {
    byte[] pdfBytes = reportService.generateReportTotalComissionByProducts(accountNumber);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDispositionFormData("filename", "reportComissionProduct.pdf");

    return Mono.just(new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK));
  }



  /**
   * .
   * GET /report/movementsCards/{numberDocument} :
   * Gets report movements from debit and credit card
   * Gets report movements from debit and credit card
   *
   * @param numberDocument number document of client (required)
   * @return successful operation (status code 200)
   *         or Invalid ID supplied (status code 400)
   *         or product not found (status code 404)
   */
  @Operation(
          operationId = "getReportMovementsCards",
          summary = "Gets report movements from debit and credit card",
          description = "Gets report movements from debit and credit card",
          tags = { "reports" },
          responses = {
              @ApiResponse(responseCode = "200", description = "successful operation"),
              @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
              @ApiResponse(responseCode = "404", description = "product not found")
          }
  )
  @RequestMapping(
          method = RequestMethod.GET,
          value = "/movementsCards/{numberDocument}",
          produces = { "application/json", "application/xml" }
  )
  public Mono<ResponseEntity<byte[]>> getReportMovementsCards(
          @Parameter(name = "numberDocument",
                  description = "number document of client",
                  required = true, in = ParameterIn.PATH)
          @PathVariable("numberDocument") String numberDocument
  ) throws IOException {
    byte[] pdfBytes = reportService.generateReportMovementsCards(numberDocument);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDispositionFormData("filename", "reportMovementsCards.pdf");

    return Mono.just(new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK));
  }


}

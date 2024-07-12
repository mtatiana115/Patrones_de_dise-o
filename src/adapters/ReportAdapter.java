package src.adapters;

import src.ports.ReportGenerator;

public class ReportAdapter implements ReportGenerator {

  /*
   * Patron estructural -> Adapter
   * Este patrón para integrar bibliotecas externas o sistemas que no tienen
   * interfaces compatibles con tu aplicación.
   * Convierte las solicitudes de tu aplicación en un formato que la biblioteca de
   * reportes pueda entender.
   */

  private ExternalReportLibrary externalReportLibrary;

  public ReportAdapter() {
    externalReportLibrary = new ExternalReportLibrary();
  }

  @Override
  public void generateReport(String reportType) {
    if (reportType.equalsIgnoreCase("PDF")) {
      externalReportLibrary.createPDFReport();
    } else if (reportType.equalsIgnoreCase("EXCEL")) {
      externalReportLibrary.createExcelReport();
    }
  }

}

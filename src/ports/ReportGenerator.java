package src.ports;

public interface ReportGenerator {

  /*
   * Patron estructural -> Adapter
   * Ver RepostAdapter
   */
  void generateReport(String reportType);
}

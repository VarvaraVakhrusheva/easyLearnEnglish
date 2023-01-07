package ru.vakhrusheva.telegram.fileProcessor;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordFileProcessorImpl {

  public FileInputStream createWordFile(List<String> taskList) throws IOException {
    // Формирование документа на основе шаблона - файла .docx из папки resources
    XWPFDocument doc =
        new XWPFDocument(getClass().getClassLoader().getResourceAsStream("Template.docx"));
    setTaskListToXWPFDocument(doc, taskList);
    return createTempFile(doc);
  }

  private void setTaskListToXWPFDocument(XWPFDocument doc, List<String> taskList) {
    // запись первой строки списка заданий в первый абзац документа (создаётся по умолчанию при
    // создании документа)
    XWPFParagraph paragraph = doc.getLastParagraph();
    XWPFRun run = paragraph.createRun();
    setRunParameters(run, taskList.get(0));
    // запись оставшихся строк списка заданий путём создания для каждой нового абзаца
    for (int i = 1; i < taskList.size(); i++) {
      paragraph = doc.createParagraph();
      run = paragraph.createRun();
      setRunParameters(run, taskList.get(i));
    }
  }

  private void setRunParameters(XWPFRun run, String task) {
    run.setFontSize(20);
    run.setFontFamily("Calibri");
    run.setText(task);
  }

  private FileInputStream createTempFile(XWPFDocument doc) throws IOException {
    File result = File.createTempFile("Print_Me", ".docx");
    try (FileOutputStream out = new FileOutputStream(result)) {
      doc.write(out);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new FileInputStream(result);
  }
}

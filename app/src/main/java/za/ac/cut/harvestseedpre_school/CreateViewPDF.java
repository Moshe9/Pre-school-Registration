//package za.ac.cut.harvestseedpre_school;
//
//import android.icu.text.SimpleDateFormat;
//import android.os.Environment;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.Date;
//
//public class CreateViewPDF extends AppCompatActivity {
//
//    String name_learner,surname_learner,tuck_balance,className,objectId;
//    TextView textView, textView2, textView3, textView4;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_view_pdf);
//        ReuseClass.setSupportActionBarView(this,"PDF Create").setIcon(R.mipmap.ic_launcher);
//        textView = (TextView) findViewById(R.id.textView);
//        textView2 = (TextView) findViewById(R.id.textView2);
//        textView3 = (TextView) findViewById(R.id.textView3);
//        textView4 = (TextView) findViewById(R.id.textView4);
//        name_learner = getIntent().getExtras().getString("name");
//        surname_learner = getIntent().getExtras().getString("surname");
//        tuck_balance = getIntent().getExtras().getString("tuckBalance");
//        className = getIntent().getExtras().getString("className");
//
//        textView.setText("Name: "+name_learner);
//        textView2.setText("Surname: "+surname_learner);
//        textView3.setText("Current Balance: "+tuck_balance);
//        textView4.setText("Class Name: "+ className);
//
//    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.cancel_menu_item, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.mnu_cancel:
//                this.finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//    public void CreatePDF(View v) {
//
//        String date = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        }
//
//        Document document = new Document();
//
//        try {
//
//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/LearnerPurchases.pdf");
//            PdfWriter.getInstance(document, new FileOutputStream(file));
//
//
//            //open
//            document.open();
//
//            Font f = new Font();
//            f.setStyle(Font.BOLD);
//            f.setSize(8);
//            Paragraph p = new Paragraph("TuckShop Report for :" + date, f);
//            p.setAlignment(Element.ALIGN_CENTER);
//
//            Font f1 = new Font();
//            f1.setStyle(Font.BOLD);
//            f1.setSize(4);
//            Paragraph p1 = new Paragraph("Learner Name: "+name_learner, f);
//            p1.setAlignment(Element.ALIGN_LEFT);
//            //add doc
//            document.add(p);
//            PdfPTable table = new PdfPTable(new float[] { 2, 2, 2 ,2, 2});
//            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell("Learner Name");
//            table.addCell("Learner Surname");
//            table.addCell("Class Name");
//            table.addCell("Balance");
//            table.addCell("Date");
//            table.setHeaderRows(1);
//            PdfPCell[] cells = table.getRow(0).getCells();
//            for (int j=0;j<cells.length;j++){
//                cells[j].setBackgroundColor(BaseColor.GRAY);
//            }
//
//            table.addCell(name_learner);
//            table.addCell(surname_learner);
//            table.addCell(className);
//            table.addCell("R"+tuck_balance);
//            table.addCell(date);
//
//            document.add(table);
//
//
//            //close
//            document.close();
//            Toast.makeText(this, "PDF created!!", Toast.LENGTH_SHORT).show();
//
//
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//            Toast.makeText(this, "PDF Not created!!" + ex.getMessage(), Toast.LENGTH_SHORT).show();
//
//        }
//
//    }
//    public void CancelPDFF(View view) {
//        this.finish();
//    }
//}

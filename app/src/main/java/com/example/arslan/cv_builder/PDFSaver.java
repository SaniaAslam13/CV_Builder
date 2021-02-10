package com.example.arslan.cv_builder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import ObjectLayer.Education;
import ObjectLayer.Experience;
import ObjectLayer.User;
import ObjectLayer.UserBasic;

    public class PDFSaver {

        final int page_Width = 592;
        final int page_Height = 848;
        final int y_gap_Heading = 50;
        final int y_gap_row = 25;
        final int y_gap_row_AfterHeading = 40;
        final int x_Gap = 50;
        final int x_HeadingGap = page_Width/2 - 70;

        Paint paint_Heading = new Paint();
        Paint paint_Text = new Paint();

        Context context;


        public PDFSaver(Context context) {

            paint_Heading.setTextSize(22);
            paint_Heading.setColor(Color.RED);

            paint_Text.setTextSize(14);
            paint_Text.setColor(Color.BLACK);

            this.context = context;

        }

    public String saveFile(User user) {

        int verticalLine = 0;

        PdfDocument document = new PdfDocument();

        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(page_Width, page_Height, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        String Resume_Name = user.getUserBasic().getFirstName() ;

        verticalLine = this.CanvasAddHeading(canvas, "Resume", verticalLine);

        verticalLine = this.writeUserBasic(canvas, user.getUserBasic(), verticalLine);

        verticalLine = this.CanvasAddHeading(canvas, "Education", verticalLine);

        verticalLine = this.writeUserEducation(canvas, user.getList_Education(), verticalLine);

        verticalLine = this.CanvasAddHeading(canvas, "Experience", verticalLine);

        verticalLine = this.writeUserExperience(canvas, user.getList_Experience(), verticalLine);


        document.finishPage(page);


        // write the document content
        String directory_path = context.getFilesDir().toString() + "/" + Resume_Name + " resume"  + ".pdf";
        File file = new File(directory_path);
        if (file.exists()) {
            file.delete();
        }

        String rtrnStr = "";

        try {
            document.writeTo(new FileOutputStream(file));
            rtrnStr = "File Saved: " + directory_path;
        }
        catch (IOException e) {
            rtrnStr = "Something wrong: " + e.toString();

        }

        document.close();
        return rtrnStr;
    }


    private int writeUserExperience(Canvas canvas, List<Experience> list_experience, int verticalLine) {

        int i= 0;

        for(Experience experience : list_experience) {

            i++;
            String header = "Experience " + Integer.toString(i) + ":  ";
            if(i==1)
                verticalLine = CanvasAddColumn_AfterHeading(canvas, header +  experience.experience(), verticalLine);
            else
                verticalLine = CanvasAddColumn(canvas, header +  experience.experience(), verticalLine);
        }

        return verticalLine;

    }

    private int writeUserEducation(Canvas canvas, List<Education> list_education, int verticalLine) {

        int i= 0;

        for(Education education : list_education) {

            i++;
            String header = "Education " + Integer.toString(i) + ":  ";

            if( i == 1)
                verticalLine = CanvasAddColumn_AfterHeading(canvas, header +  education.education(), verticalLine);
            else
                verticalLine = CanvasAddColumn(canvas, header +  education.education(), verticalLine);
        }

        return verticalLine;
    }


    private int writeUserBasic(Canvas canvas ,UserBasic userBasic, int verticalLine ) {

        String firstName = "First Name : " + userBasic.getFirstName();
        String lastName = "Last Name : " + userBasic.getLastName();
        String email = "Email : " + userBasic.getEmail();
        String address = "Address : " + userBasic.getAddress();
        String number = "Number : " + userBasic.getNumber();


        verticalLine = CanvasAddColumn_AfterHeading(canvas, firstName, verticalLine);
        verticalLine = CanvasAddColumn(canvas, lastName, verticalLine);
        verticalLine = CanvasAddColumn(canvas, number, verticalLine);
        verticalLine = CanvasAddColumn(canvas, email, verticalLine);
        verticalLine = CanvasAddColumn(canvas, address, verticalLine);

        return verticalLine;

    }

    private int CanvasAddHeading(Canvas canvas, String line,  int verticalLine ) {

        verticalLine = verticalLine + y_gap_Heading;
        canvas.drawText(line, x_HeadingGap, verticalLine, paint_Heading);
        return verticalLine;
    }

    private int CanvasAddColumn(Canvas canvas, String line,  int verticalLine ) {

        verticalLine = verticalLine + y_gap_row;
        canvas.drawText(line, x_Gap, verticalLine, paint_Text);
        return verticalLine;
    }

    private int CanvasAddColumn_AfterHeading(Canvas canvas, String line,  int verticalLine ) {

        verticalLine = verticalLine + y_gap_row_AfterHeading;
        canvas.drawText(line, x_Gap, verticalLine, paint_Text);
        return verticalLine;
    }





}



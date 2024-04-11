package fr.transport.modele;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.importation.LectureCSV;

public class FileToLigneTransport
{
    public static LigneTransport LireFichierAndroid( Context context, String fileName )
    {
        LigneTransport ligneTransport = null;

        try
        {
            // Copier le fichier depuis le dossier "assets" vers un emplacement externe
            File externalFile = new File( context.getExternalFilesDir(null), fileName );

            // Ouvrir un flux pour le fichier dans le dossier "assets"
            try (InputStream inputStream = context.getAssets().open(fileName);

            // Ouvrir un flux de sortie pour le fichier externe
            OutputStream outputStream = new FileOutputStream(externalFile))
            {

                // Copier le contenu du fichier depuis le flux d'entrée vers le flux de sortie
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0)
                {
                    outputStream.write(buffer, 0, length);
                }

                // Lecture du fichier
                LectureCSV lecture = new LectureCSV(externalFile);
                ligneTransport = lecture.getLigneTransport();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            // Supprimer le fichier externe s'il existe
            if (externalFile.exists())
            {
                externalFile.delete();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ligneTransport;
    }
}

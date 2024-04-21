package fr.transport;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.transport.modele.Metier;
import fr.transport.modele.entite.Jour;
import fr.transport.modele.entite.Lieu;
import fr.transport.modele.entite.LigneTransport;
import fr.transport.modele.entite.Periode;
import fr.transport.modele.entite.Trajet;
import fr.transport.modele.filtrage.*;

public class Controleur extends Metier implements Serializable
{
	public Controleur()
	{
		super();
	}
}

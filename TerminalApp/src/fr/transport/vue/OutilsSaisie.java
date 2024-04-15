package fr.transport.vue;

public class OutilsSaisie
{
	private static final String MESSAGE_ERREUR = "Erreur de saisie, veuillez recommencer.";

	private OutilsSaisie() {}

	public static final int saisirEntier( String message )
	{
		try
		{
			System.out.print( message );
			return Integer.parseInt( System.console().readLine() );
		}
		catch( NumberFormatException e )
		{
			System.out.println( MESSAGE_ERREUR );
			return saisirEntier( message );
		}
	}

	public static final String saisirChaine( String message )
	{
		try
		{
			System.out.print( message );
			return System.console().readLine();
		}
		catch( NumberFormatException e )
		{
			System.out.println( MESSAGE_ERREUR );
			return saisirChaine( message );
		}
	}
}

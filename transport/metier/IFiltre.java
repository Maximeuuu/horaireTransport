/*==============================================================================
Auteur        : Maxime Lemoine
Date création : 06/05/23
Dernière maj  : 07/05/23
Version       : 1
==============================================================================*/

package transport.metier;

public interface IFiltre
{
	public Object[][] filtrer( Object[][] tab );

	public boolean includeRow( Object[] row );
}

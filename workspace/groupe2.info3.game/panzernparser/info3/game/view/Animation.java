package info3.game.view;

import java.awt.Image;
import java.util.HashMap;

import info3.game.GameConfiguration.ActionDirection;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;

/**
 * Classe qui représente l'animation décrite dans le fichier <b>.ani</b> pour un
 * sprite en particulier. Cette classe doit définir une séquence de sprite dans
 * l'image pour chaque action de l'automate (pop/wizz/turn/etc.).
 */
public class Animation {

	Sprite m_sprite;
	HashMap<ActionDirection, int[]> m_animationSequence;
	
	public Animation(Sprite sprite, HashMap<ActionDirection, int[]> animationSequence) {
		m_sprite = sprite;
		m_animationSequence = animationSequence;
	}
	
	public Image getImage(double ActionProgress, LsAction ac, MyDirection dir) {
		ActionDirection aD = new ActionDirection(ac,dir);
		if (ac == null) {
			//TODO : definir l'affichage dans le cas ou il n'y a pas d'action en cours
			return null;
		}
		int[] seq = m_animationSequence.get(aD);
		int i = (int)(ActionProgress * seq.length);
		if (i >= seq.length) i = seq.length -1;
		return m_sprite.getSprite(seq[i]);
	}
	
	// TODO : ajouter une animation pour chaque actions (wizz/pop/turn/...)
}

package info3.game.view;

import java.awt.Image;
import java.util.HashMap;

import info3.game.GameConfiguration.ActionDirection;
import info3.game.automaton.MyDirection;
import info3.game.automaton.action.LsAction;
import info3.game.model.Model.VisionType;

/**
 * Classe qui représente l'animation décrite dans le fichier <b>.ani</b> pour un
 * sprite en particulier. Cette classe doit définir une séquence de sprite dans
 * l'image pour chaque action de l'automate (pop/wizz/turn/etc.).
 */
public class Animation {

	String m_name;
	Sprite[] m_sprites;
	HashMap<ActionDirection, int[]> m_animationSequence;

	public Animation(Sprite[] sprites, HashMap<ActionDirection, int[]> animationSequence,String name) {
		m_name = name.substring(0, name.length()-4);
		m_sprites = sprites;
		m_animationSequence = animationSequence;
	}

	public Image getImage(double ActionProgress, LsAction ac, MyDirection dir, VisionType vision) {
		if (ac == null) {
			//dans le cas ou il n'y a pas d'action en cours
			return null;
		}

		Sprite currSprite;
		switch (vision) {
			case ENEMIES:
				currSprite = m_sprites[1]; // deuxieme ligne du .ani
				break;
			case RESSOURCES:
				currSprite = m_sprites[2]; // troisieme ligne du .ani
				break;
			default:
				currSprite = m_sprites[0]; // premiere ligne du .ani
				break;
		}

		ActionDirection aD = new ActionDirection(ac, dir);
		int[] seq = m_animationSequence.get(aD);
		if (seq == null) {
			//dans le cas ou il n'y a pas d'animation associcée
			return null;
		}
		int i = (int) (ActionProgress * seq.length);
		if (i >= seq.length)
			i = seq.length - 1;
		return currSprite.getSprite(seq[i]);
	}
	
	public String getName() {
		return m_name;
	}

	@Override
	public String toString() {
		return m_name;
	}
	
	
}

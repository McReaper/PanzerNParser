package info3.game.view;

import java.util.HashMap;

import info3.game.automaton.action.LsAction;

/**
 * Classe qui représente l'animation décrite dans le fichier <b>.ani</b> pour un
 * sprite en particulier. Cette classe doit définir une séquence de sprite dans
 * l'image pour chaque action de l'automate (pop/wizz/turn/etc.).
 */
public class Animation {

	Sprite m_sprite;
	HashMap<LsAction, int[]> m_animationSequence;
	
	public Animation(Sprite sprite, HashMap<LsAction, int[]> animationSequence) {
		m_sprite = sprite;
		m_animationSequence = animationSequence;
	}
	
	// TODO : ajouter une animation pour chaque actions (wizz/pop/turn/...)
}

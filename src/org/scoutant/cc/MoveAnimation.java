package org.scoutant.cc;

import java.util.ArrayList;
import java.util.List;

import org.scoutant.cc.model.Move;
import org.scoutant.cc.model.Point;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

public class MoveAnimation {
	@SuppressWarnings("unused")
	private static String tag = "animation";
	private static final int DURATION = 800;

	private PegUI peg;

	private Point first;
	private int Dx;
	private int Dy;
	
	private List<Animator> animators = new ArrayList<Animator>();

	// assuming peg actually is at position corresponding to first point in move...
	public MoveAnimation( PegUI peg, Move move) {
		this.peg = peg;
		this.first = move.first();
		Point from = first;
		Dx= -dx( move.last(), first);
		Dy= -dy( move.last(), first);
//		AnimatorSet translateXY = new AnimatorSet();
//		translateXY.play(ObjectAnimator.ofFloat(peg, "translationX", -Dx))
//		.with(ObjectAnimator.ofFloat(peg, "translationY", -Dy));
//		translateXY.setDuration(0);
//		animators.add(translateXY);
		peg.setTranslationX(-Dx);
		peg.setTranslationY(-Dy);
		
		for (int k=1; k<move.points.size(); k++) {
			Point to = move.point(k);
			this.add( dx(from,to), dy(from,to));
		}
	}

	public void add(int dx, int dy) {
		AnimatorSet translateXY = new AnimatorSet();
		translateXY.play(ObjectAnimator.ofFloat(peg, "translationX", -Dx+dx))
		.with(ObjectAnimator.ofFloat(peg, "translationY", -Dy+dy));
		translateXY.setDuration(DURATION);
		animators.add(translateXY);
	}
	
	public void start() {
		AnimatorSet set = new AnimatorSet();
		set.playSequentially(animators);
		set.start();
	}

	private int dx(Point from, Point to) {
		return peg.game.pixel(to).x - peg.game.pixel(from).x; 
	}
	private int dy(Point from, Point to) {
		return peg.game.pixel(to).y - peg.game.pixel(from).y; 
	}
}
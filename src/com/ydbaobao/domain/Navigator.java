package com.ydbaobao.domain;

import java.util.stream.IntStream;

public class Navigator {
	private final int LIMIT_PAGE = 10;

	private final int prevBlock;
	private final int nextBlock;
	private final int[] range;
	private final int selected;

	public Navigator(int selected, int lastPage) {
		this.range = range(selected, lastPage);
		this.selected = selected;
		this.prevBlock = this.prev(range);
		this.nextBlock = this.next(range, lastPage);
	}

	private int[] range(int selected, int lastPage) {
		return IntStream.range(this.startPage(selected),
				this.endPage(selected, lastPage)).toArray();
	}

	private int prev(int[] pages) {
		if (pages.length == 0) {
			return 0;
		}
		return pages[0] - 1;
	}

	private int next(int[] pages, int lastPage) {
		if (pages.length == 0) {
			return 0;
		}
		int next = pages[pages.length - 1] + 1;
		return next < lastPage ? next : -1;
	}

	/**
	 * 페이징 기능에서 블록 별 첫번째 숫자를 구한다
	 * 
	 * @param 현재
	 *            페이지
	 * @return 페이징에서 첫 번째 수
	 */
	private int startPage(int page) {
		int block = page / LIMIT_PAGE + 1;
		if (page % LIMIT_PAGE == 0) {
			block -= 1;
		}
		return (block - 1) * LIMIT_PAGE + 1;
	}

	/**
	 * 페이징 기능에서 블록 별 마지막 숫자를 구한다
	 * 
	 * @param 현재
	 *            페이지, 전체 페이지 수
	 * @return 페이징에서 마지막 페이지 수(for 조건문 때문에 출력하고자 하는 수보다 1을 더해준다)
	 */
	private int endPage(int page, int lastPage) {
		int block = page / LIMIT_PAGE + 1;
		if (page % LIMIT_PAGE == 0) {
			block -= 1;
		}
		// 마지막 페이질 경우
		if ((block - 1) * LIMIT_PAGE + LIMIT_PAGE >= lastPage) {
			return lastPage + 1;
		}
		return (block - 1) * LIMIT_PAGE + LIMIT_PAGE + 1;
	}

	public int getSelected() {
		return this.selected;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public int[] getRange() {
		return range;
	}

}

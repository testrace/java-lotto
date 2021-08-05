package lottosecond.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Lotto {

	public static final int NUMBERS_SIZE_LIMIT = 6;

	private final Set<LottoNumber> numbers;

	public Lotto(Set<LottoNumber> numbers) {
		if (validateSize(numbers.size())) {
			throw new LottoNumbersSizeException();
		}
		this.numbers = numbers;
	}

	private boolean validateSize(int size) {
		return size != NUMBERS_SIZE_LIMIT;
	}

	public Set<LottoNumber> numbers() {
		return Collections.unmodifiableSet(numbers);
	}

	public int matchingQuantityFrom(List<Integer> lastWeekNumbers) {
		int matchedCount = 0;
		for (Integer lastWeekNumber : lastWeekNumbers) {
			matchedCount += getMatchedCount(lastWeekNumber);
		}
		return matchedCount;
	}

	private int getMatchedCount(Integer lastWeekNumber) {
		return numbers.contains(LottoNumber.valueOf(lastWeekNumber)) ? 1 : 0;
	}

	public boolean hasBonus(int bonusNumber) {
		return numbers.contains(LottoNumber.valueOf(bonusNumber));
	}

	@Override
	public String toString() {
		return numbers().toString();
	}

}

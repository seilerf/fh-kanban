package edu.fh.kanban.dao;

import edu.fh.kanban.domain.Preference;

public interface PreferenceDAO {
	public Preference findPreference();
	public int insertPreference();
	public boolean deletePreference();
	public boolean updatePreference();
}

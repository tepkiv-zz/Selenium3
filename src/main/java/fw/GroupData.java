package fw;


public class GroupData implements Comparable<GroupData>{
	private String name;
	private String header;
	private String footer;

	public GroupData(String groupname, String header, String footer) {
		this.name = groupname;
		this.header = header;
		this.footer = footer;
	}
	public GroupData(){}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("GroupData{");
		sb.append("name='").append(name).append('\'');
		sb.append(", header='").append(header).append('\'');
		sb.append(", footer='").append(footer).append('\'');
		sb.append('}');
		return sb.toString();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GroupData groupData = (GroupData) o;

		if (name != null ? !name.equals(groupData.name) : groupData.name != null) return false;
		if (header != null ? !header.equals(groupData.header) : groupData.header != null) return false;
		return footer != null ? footer.equals(groupData.footer) : groupData.footer == null;

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (header != null ? header.hashCode() : 0);
		result = 31 * result + (footer != null ? footer.hashCode() : 0);
		return result;
	}

	public int compareTo(GroupData other) {
		return this.name.toLowerCase().compareTo(other.name.toLowerCase());
	}

	public GroupData withName(String name){
		this.name = name;
		return this;
	}

	public GroupData withHeader(String header){
		this.header = header;
		return this;
	}
	public GroupData withFooter(String footer){
		this.footer = footer;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}
}

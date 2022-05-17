package com.iktpreobuka.egradebook.security;

public class Views {

	public static class Admin extends Headmaster {
	}

	public static class Headmaster extends HomeroomT {
	}

	public static class HomeroomT extends Teacher {
	}

	public static class Parent extends Student {
	}

	public static class Student {
	}

	public static class SuperAdmin extends Admin {

	}

	public static class Teacher extends Parent {
	}
}

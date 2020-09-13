using UnityEngine;
using System.Collections;

public class FollowTarget : MonoBehaviour {
	public Transform player1;
	public Transform player2;
	private Camera cameras;
	private Vector3 offset;
	// Use this for initialization
	void Start () {
		offset = transform.position - (player1.position + player2.position) / 2;//偏移量
		cameras = this.GetComponent<Camera>();
	}
	
	// Update is called once per frame
	void Update () {
		if (player1 == null || player2 == null) return;
		transform.position = (player1.position + player2.position) / 2 + offset;//两个坦克中心位置加上偏移量
		float distance = Vector3.Distance(player1.position, player2.position);
		float size = distance * 1;//相机size值与两个坦克之间距离的比例；
		cameras.orthographicSize = size;
	}
}

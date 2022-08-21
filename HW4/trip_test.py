import unittest
import trip
import io
from unittest import mock

f = io.StringIO()

class TestProgram(unittest.TestCase):
    @mock.patch('trip.stdin')
    @mock.patch('trip.stdout')
    def test_cases(self, mock_stdout, mock_stdin):
        for i in range(1, 11):
            with open(f'./input{i}.txt', 'r') as in_file:
                with open(f'./output{i}.txt', 'r') as out_file:
                    writer = io.StringIO()
                    mock_stdout.write = writer.write
                    print(f'TEST {i}')
                    inp = in_file.readlines()
                    mock_stdin.readline.side_effect = inp
                    trip.main()
                    out = out_file.read()
                    self.assertEqual(writer.getvalue(), out)


if __name__ == '__main__':
    unittest.main()